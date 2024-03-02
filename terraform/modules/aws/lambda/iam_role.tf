resource "aws_iam_role" "iam_for_lambda" {
  name               = var.iam_role_lambda
  assume_role_policy = data.aws_iam_policy_document.assume_role.json
}
