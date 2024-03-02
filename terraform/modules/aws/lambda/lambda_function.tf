resource "aws_lambda_function" "current" {

  # Lambda basic configs
  function_name = var.lambda_name
  description   = var.lambda_description
  role          = aws_iam_role.iam_for_lambda.arn
  memory_size   = var.lambda_memory_size
  tags          = var.lambda_tags
  environment {
    variables = var.lambda_env_variables
  }

  # Deployment package
  filename         = "lambda_deployment_package.zip"
  handler          = var.lambda_handler
  source_code_hash = data.archive_file.lambda.output_base64sha256
  runtime          = var.lambda_runtime
}
