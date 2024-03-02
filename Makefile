#!/usr/bin/env make

SHELL := /bin/bash
.DEFAULT_GOAL := help

###########################
# VARIABLES
###########################

ifeq ($(OS),Windows_NT) # set Python , depending if user is in windows or linux
    PYTHON = python
else
    PYTHON = python3
endif


mkfile_path := $(abspath $(lastword $(MAKEFILE_LIST)))
mkfile_dir := $(dir $(mkfile_path))

GITGUARDIAN_NUM_COMMITS ?= 1

.PHONY: help
help:  ## help to show available commands with info
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) |  awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

#################
#
# Run the following lines for setting up your venv
# afterwards you must do a source .venv/bin/activate
#
##################

.PHONY: setup-venv
setup-venv: ## setup venv if needed
	${PYTHON} -m venv .venv
	. ${mkfile_dir}.venv/bin/activate

.PHONY: install-deps
install-deps: ## install python deps
	${PYTHON} -m pip install -r src/requirements.txt

#################
#
# Terraform
#
##################

.PHONY: tf-init
tf-init: ## run tf init
	terraform -chdir="./terraform/apps/12_months_free/vpn" init

.PHONY: tf-plan
tf-plan: ## run tf plan
	 terraform -chdir="./terraform/apps/12_months_free/vpn" plan -var-file=../../../envs/prod/common/env.tfvars -var-file=../../../envs/prod/app/12_months_free/passbolt/env.tfvars

.PHONY: tf-apply
tf-apply: ## run tf apply
	terraform -chdir="./terraform/apps/12_months_free/vpn" apply -var-file=../../../envs/prod/common/env.tfvars -var-file=../../../envs/prod/app/12_months_free/passbolt/env.tfvars


.PHONY: secret-scan
secret-scan:  ## run secret scan with GitGuardian
	ggshield secret scan path ../../ --recursive

.PHONY: secret-scan-commit-range
secret-scan-commit-range:  ## run secret scan for git commit range with GitGuardian
	@read -p "Choose the number of commits starting from $(GITGUARDIAN_NUM_COMMITS): " num_commits; \
	if [ -z "$$num_commits" ]; then \
		num_commits="$(GITGUARDIAN_NUM_COMMITS)"; \
	else \
		num_commits="$$num_commits"; \
	fi; \
	echo "Command: ggshield secret scan commit-range HEAD~$$num_commits"
	ggshield secret scan commit-range "HEAD~$$num_commits"
