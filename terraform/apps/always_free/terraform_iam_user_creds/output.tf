output "access_key" {
  value     = aws_iam_access_key.admin_access_key.id
  sensitive = true
}

output "secret_key" {
  value     = aws_iam_access_key.admin_access_key.secret
  sensitive = true
}
