# module "global_variables" {
#   source = "../../../envs/prod/app/12_months_free/passbolt"
# }

# module "ec2_instance_passbolt" {
#   source            = "../../../modules/aws/ec2"
#   ec2_name          = module.global_variables.ec2_name
#   ec2_ami           = module.global_variables.ec2_ami
#   ec2_instance_type = module.global_variables.ec2_instance_type
#   ec2_tags          = module.global_variables.ec2_tags
# }


# module "lambda_function_sendgrid" {
#   source = "../../../modules/aws/lambda"

#   lambda_output_path   = module.global_variables.lambda_output_path
#   lambda_description   = module.global_variables.lambda_description
#   lambda_handler       = module.global_variables.lambda_handler
#   lambda_name          = module.global_variables.lambda_name
#   lambda_runtime       = module.global_variables.lambda_runtime
#   lambda_source_dir    = module.global_variables.lambda_source_dir
#   lambda_env_variables = module.global_variables.lambda_env_variables
#   lambda_memory_size   = module.global_variables.lambda_memory_size
#   iam_role_lambda      = module.global_variables.iam_role_lambda
#   lambda_tags          = module.global_variables.lambda_tags
# }
