RMI Distributed Object Implementation

Introduction
This practical focus is on adapting the LifeCo system to use RMI for communication between components. The goal is to create a distributed system where each component can run in a separate Docker container.

Components
core: Contains shared code, including distributed object interfaces and data classes.
auldfellas, dodgygeezers, girlsallowed: Quotation services providing quotes based on client information.
broker: The broker service locates quotation services using the RMI registry and aggregates quotations.
client: Client services request quotations from the broker.


Module Integration: 
The core code serves as the foundation for the entire system. It defines the QuotationService interface, which specifies methods for obtaining quotations. Each quotation service module auldfellas, dodgygeezers, girlsallowed implements this interface. This step establishes a common contract for all services to adhere to when providing quotations.

Broker Service: 
The broker service acts as a coordinator between clients and quotation services. It utilizes RMI for communication. The broker exports itself as an RMI object, making its methods available for remote invocation. Additionally, the broker registers with the RMI registry, making it discoverable by other components. 

Maven and POM:
The parent POM centralizes common configurations, dependencies, and plugins for the entire distributed system project, where we have specified all the modules. Module-specific POM files extend and customize configurations, dependencies, and settings to meet the requirements of individual components, such as services, clients, and shared code. Parent and module POMs are configured to seamlessly manage distributed object communication using RMI and streamline Docker containerization, enhancing the building and deployment process of the application.

Client Interaction: 
Clients interact with the system through the broker service via RMI. They request quotations by invoking methods on the broker, providing specific client profiles as input. The broker, in turn, uses RMI to locate and communicate with the appropriate quotation services auldfellas, dodgygeezers, girlsallowed to collect quotations. This step encapsulates the client from the complexity of locating and communicating with multiple services, simplifying the client's interaction with the system.

Docker Containerization:
Docker containerization is used to encapsulate each component core, quotation services, broker, and client into separate, lightweight containers.
The docker-compose.yml serves as an essential tool for managing the complex deployment of the distributed system.
1. Docker Compose allows to define and manage multiple services, ensuring that each component of the system is correctly deployed and can communicate with others.
2. We can specify dependencies between services, ensuring that services like clients start only when necessary components broker are running. This dependency management streamlines the startup process. 

This architecture simplifies the development and deployment of a distributed system, making it more robust and scalable. Clients can request quotations without needing to know the complexities of service discovery and communication. Docker containers encapsulate components, making deployment easy and smooth.
