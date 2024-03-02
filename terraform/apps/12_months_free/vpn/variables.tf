variable "region" {
  description = "AWS region"
  type        = string
  default     = "eu-west-1"

}

variable "env" {
  description = "Project environment"
  type        = string
}

variable "ec2_name" {
  description = "Name of the passbolt ec2 instance"
  type        = string

}

variable "ec2_ami" {
  description = "AMI ID on Ireland"
  type        = string

}

variable "ec2_instance_type" {
  description = "The type of instance on EC2"
  type        = string

}


variable "ec2_tags" {
  description = "tags for the ec2 instance"
  type        = map(string)

}
