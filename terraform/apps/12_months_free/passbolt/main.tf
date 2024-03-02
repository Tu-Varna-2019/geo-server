module "ec2_instance_passbolt" {
  source            = "../../../modules/aws/ec2"
  ec2_name          = var.ec2_name
  ec2_ami           = var.ec2_ami
  ec2_instance_type = var.ec2_instance_type
  ec2_tags          = var.ec2_tags
}


module "lambda_function_sendgrid" {
  source = "../../../modules/aws/lambda"

  lambda_output_path   = var.lambda_output_path
  lambda_description   = var.lambda_description
  lambda_handler       = var.lambda_handler
  lambda_name          = var.lambda_name
  lambda_runtime       = var.lambda_runtime
  lambda_source_dir    = var.lambda_source_dir
  lambda_env_variables = var.lambda_env_variables
  lambda_memory_size   = var.lambda_memory_size
  iam_role_lambda      = var.iam_role_lambda
  lambda_tags          = var.lambda_tags
}
