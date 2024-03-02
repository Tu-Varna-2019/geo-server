output "ec2_instance" {
  value       = aws_instance.app_server.arn
  description = "EC2 instance ARN output to be referenced to other modules"

}
