ec2_name          = "prod-personal-openvpn-ec2-aws"
ec2_ami           = "ami-06e118222019a14ed"
ec2_instance_type = "t2.micro"
ec2_tags = {
  "env"     = "prod"
  "type"    = "personal"
  "service" = "ec2"
  "app"     = "OpenVPN"
}
