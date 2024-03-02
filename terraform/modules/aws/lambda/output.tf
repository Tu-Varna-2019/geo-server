output "lambda_function" {
  value       = aws_lambda_function.current.arn
  description = "Lambda arn output to be referenced to other modules"

}
