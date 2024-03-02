ec2_name          = "prod-personal-passbolt-ec2-aws"
ec2_ami           = "ami-090387226c863035b"
ec2_instance_type = "t2.micro"
ec2_tags = {
  "env"     = "prod"
  "type"    = "personal"
  "service" = "ec2"
  "app"     = "passbolt"
}

# Lambda configs
lambda_name = "prod-personal-sendgrid-lambda-aws"
#lambda_function_file_path = "src/lambda/SendGridMessage"
lambda_handler     = "index.py"
lambda_runtime     = "python3.10"
lambda_source_dir  = "../../../../src/lambda/SendGridMessage"
lambda_output_path = "../../../../src/lambda/lambda_deployment_package.zip"

# IAM configs
iam_role_lambda = "prod-personal-sendgrid-iam-aws"

lambda_env_variables = {
  "key" = "value"
}
lambda_tags = {
  "env"     = "dev"
  "type"    = "personal"
  "service" = "lambda"
  "app"     = "sendgrid"
}


