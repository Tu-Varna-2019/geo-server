
module "ec2_instance_vpn" {
  source            = "../../../modules/aws/ec2"
  ec2_name          = var.ec2_name
  ec2_ami           = var.ec2_ami
  ec2_instance_type = var.ec2_instance_type
  ec2_tags          = var.ec2_tags
}
