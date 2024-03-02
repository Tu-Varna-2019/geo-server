# Introduction

This project is related to managing AWS resource configuration & deployment for Iliyan's personal AWS account.

## Getting Started

TODO: Repository contains the agreed folder structure that the project should contain.
Each folder reflects an Azure resource.
Please remove unnecessary folders from the repository.

TODO: Guide users through getting your code up and running on their own system. In this section you can talk about:

1. Installation process
2. Software dependencies
3. Latest releases
4. API references

## Build and Test

TODO: Describe and show how to build your code and run the tests.

## Contribute

TODO: Explain how other users and developers can contribute to make your code better.

If you want to learn more about creating good readme files then refer the following [guidelines](https://docs.microsoft.com/en-us/azure/devops/repos/git/create-a-readme?view=azure-devops). You can also seek inspiration from the below readme files:

- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore)

## Manually create AWS Identiy Center and associate it as a service provider to the Azure AD (Entra ID) from the Backsy tenant

**source:** [Microsoft doc page](https://learn.microsoft.com/en-us/entra/identity/saas-apps/aws-single-sign-on-tutorial)

1. Enable IAM Identity Center
2. Go to Azure > Entra ID > Enterprise Apps > New application >  Type AWS IAM Identity Center (successor to AWS Single Sign-On) > Type a name like `AWS SSO - Alient <last 4 numbers of your AWS account ID >` > Click Create
3. After that, you need to establish a trust relationship between Entra ID and IAM Identity Center. First go to your newly created Enterprise App > Signle sign-on > Click on `SAML`.
4. Then on the SAML page, click the pencil icon `Edit` for `Basic SAML config`
5. Click on the button `Upload metadata file`
6. Now on the AWS Identity Center side, click on `Customize AWS access portal` URL part and paste the subdomain, that should follow the same domain standards as the rest of the other AWS personal accounts: `portal-alien-aws`
7. Click on `Identity source` > Actions > Change Identity source > Choose `External identity provider` > Also please copy on the AWS side `AWS access portal sign-in URL` part and paste it along after you upload the metadata file on the Azure side named `Sign on URL (Optional)`
8. Now go back to the Azure SAML config page and click on the button `Upload metadata file` and choose the one that AWS SSO you downloaded to
9. Now go back to Azure SAML, and go to `SAML Certifiacte` to download the `Federation Metadata XML` file, which then you can upload it on the AWS side under `IdP SAML metadata`
10. Click Next > Type `Accept`
11. Next go back to Azure Side and go to `Provisioning` for SCIM sync between Azure AD users and AWS IAM users
12. Choose on the `Povisioning mode` being `Automatic` and paste the folowing creds:

- Tenant URL = AWS side `SCIM endpoint` Automatic provisioning
- Seret Token: = AWS side `Access token`
- You can generate that when you go back to AWS Identity Center > Identity Source > Actions > Manage Provisioning > Generate token

13. Now it's time to add users into the Azure provisioning > Click on Users > Click add > Type BTG
14. And finally go back to the `Provisioning page` and click `Start proisioning`
15. Go to permissionsets on the AWS side and add the following:

- Policy for predefined permission set> Select an AWS managed policy > AdministratorAccess
- Name: `BreakingTheGlass-Administrator`
- Description: `Admin privilege for emergency`

16. Next go to `AWS accounts` > Assign users or groups
17. Link the BTG to the created permissionsets
18. Voalla, you are ready!
