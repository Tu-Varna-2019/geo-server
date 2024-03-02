output "ec2_name" {
  value = "test"
}

output "ec2_instance_type" {
  value = "t2.micro"
}

output "ec2_tags" {
  value = {
    "env"     = "prod"
    "type"    = "personal"
    "service" = "ec2"
    "app"     = "passbolt"
  }
}

output "ec2_ami" {
  value = "ami-0c55b159cbfafe1f0"
}

# Lambda configs
output "lambda_name" {
  value = "prod-personal-sendgrid-lambda-aws"
}

output "lambda_description" {
  value = "Deployed by Terraform"
}

output "lambda_function_file_path" {
  value = "src/lambda/SendGridMessage"
}

output "lambda_handler" { value = "index.py" }

output "lambda_runtime" { value = "python3.9" }
output "lambda_source_dir" { value = "../../../../src/lambda/SendGridMessage" }

output "lambda_output_path" {
  value = "lambda_deployment_package.zip"
}

# IAM configs
output "iam_role_lambda" {

  value = "prod-personal-sendgrid-iam-aws"
}

output "lambda_env_variables" {
  value = {
    "key" = "value"
  }
}

output "lambda_tags" {
  value = {
    "env"     = "dev"
    "type"    = "personal"
    "service" = "lambda"
  "app" = "sendgrid" }
}

output "lambda_memory_size" {
  value = 512

}

output "env" {
  value = "prod"

}

output "region" {
  value = "eu-west-1"

}




# ec2_instance_type = "t2.micro"
# ec2_tags = {
#   "env"     = "prod"
#   "type"    = "personal"
#   "service" = "ec2"
#   "app"     = "passbolt"
# }

# # Lambda configs
# lambda_name = "prod-personal-sendgrid-lambda-aws"
# #lambda_function_file_path = "src/lambda/SendGridMessage"
# lambda_handler     = "index.py"
# lambda_runtime     = "python3.9"
# lambda_source_dir  = "../../../../src/lambda/SendGridMessage"
# lambda_output_path = "lambda_deployment_package.zip"

# # IAM configs
# iam_role_lambda = "prod-personal-sendgrid-iam-aws"

# lambda_env_variables = {
#   "key" = "value"
# }
# lambda_tags = {
#   "env"     = "dev"
#   "type"    = "personal"
#   "service" = "lambda"
#   "app"     = "sendgrid"
# }


