# Demo of Java Enhancement for azd 

## 0. Intro 

I am excited to introduce the recent enhancements we have made to the `azd` tool. These improvements enable Java 
developers to bring their applications to Azure quicker than ever.

## 1. azd’s current experience of current existing project and recap its key problem 

Let's start with an existing project that is based on the Java todo project. This project uses a PostgreSQL database as 
the backing store for the todo items created in the application. Here is the system diagram of the todo project:

> ![todo-app-system-diagram](assets/todo-app-system-diagram.png)

Let's start by running `azd init` in this repository. As you can see, the PostgreSQL database is missing. To ensure that
the application can run, users will need to update the bicep files to create PostgreSQL, and they also need to figure 
out the endpoint of the backend (api) to call from the frontend (web).

In summary the problems are:

   1. PostgreSQL is not detected.
   2. Azure Database for PostgreSQL is not provisioned. 

## 2. Show azd Enhancement for Java experience for the same project 

Now, let's move on to the Java enhancements we have made to azd. We will use `azd init` to generate the improved 
azure.yaml. This new format of azure.yaml describes the services, the service bindings, and the Azure resources. After 
that, we will call `azd up` to provision the resources and deploy the services.

Steps: 

   1. Run `azd init` to generate the azure.yaml.
   2. Show and explain azure.yaml.
   3. Run `azd up`.

## 3. Show it for new project as well

Next, we will demonstrate the ability on a newly created project. We will use VS Code to create a new Java project and 
use the Spring Initializr to select web and Service Bus dependencies. Then, we will run `azd init` and `azd up`.

Steps:

   1. VS code -> Java: new project -> Spring boot -> selected dependencies (web app with service bus)
   2. Run `azd init` & `azd up`.

## 4. Recap its advantage via a diagram

Finally, we will recap the advantages in a diagram. These enhancements save time, reduce complexity, and ensure a more 
efficient workflow.

To wrap up, the enhancements to the `azd` tool significantly streamline the process of analyzing, provisioning, and 
deploying Java applications to Azure. These improvements save time, reduce complexity, and ensure a more efficient 
workflow. Thank you for watching, and I look forward to seeing how you leverage these enhancements in your projects.

