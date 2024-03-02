terraform {
  cloud {
    organization = "iliyangit-personal-tf-org"

    workspaces {
      name = "passbolt"
    }
  }

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "5.38.0"
    }
  }

  required_version = ">= 1.7.3"
}

provider "aws" {
  region = var.region

}

