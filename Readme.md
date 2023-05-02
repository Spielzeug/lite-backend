# sevDesk Lite - Backend

### Setup
prerequisites: installed Java 17 runtime, minikube, kubectl, helm, docker

### To run on localhost:8081
- Navigate to the backend directory: `cd ./lite`
- Install the dependencies: `./gradlew build`
- Start the application by running: `./gradlew bootRun`
### To run on minikube (or your cluster)
- build docker images and push them to minikube (or your cluster) docker registry
- install helm chart provided in helm/chart/sevdesk-lite
- optionally install helm chart for frontend as well
- run minikube ip and add it to your hosts file to resolve to sevdesk-backend.lite
- alternatively you can set up ingress-dns addon for minikube
- login with admin:changeme

## Exercises
- **"Tests, where?"** - `quality`
  - Added test, they are sadly not exhaustive, since I was focused on different tasks and it didn't make sense to write tests before I restructured the application
- **"Where is my Invoice?"** - `database`
  - This task no longer possible, since I made invoices append only. Removing invoice is for sure not a good idea
- **"Fast Forward"** - `feature`
  - I implemented append only for invoices with oneToOne mapping in Invoices entity, PRECEDING_INVOICE_ID as join column and update logic in InvoiceService 
- **"these are not the droids you're looking for"** - `security`
  - I added basic auth as part of the ingress deployment. Overall if the API is only used by cluster-internal services, I would not add any auth. For apis and services accessible from outside I would consider using external auth provider configured in ingress 
- **"This building looks weird"** - `architecture`
  - I modified the architecture to horizontal layers rather then vertical, use-case based ones. In this process I also restified the controllers as well as decoupled entities from controller by introducing DTOs and mapping with mapStruct 
- **"I have my head in the clouds"** - `feature`
  - I added dockerfile to both frontend and backend. They build images that I use in my deployments. Both are running fine, but on the frontend side I couldn't find which configuration leads to service to refuse connections. Originally I planned to have an ingress secured with basic auth routing to frontend service which in turn would send requests to backend. Sadly I couldn't figure it out in the short time and decided to add basic auth secured ingress that would route requests to backend service. 
  - As far as talking to it as single application, I would prefer 2 ingresses, so I can use api and frontend separately

### Troubleshooting and Hints
- If you use IntelliJ find in `src/test/demo.http` a few requests to quick check the functionality and to generate a first few invoices in your local DB
- you can use http://localhost:8081/h2-console/ to browse the local Database, connection-string is the one you find in the `src/resources/application.yml`
- if you use a Mac and want to connect to the generated H2-Database maybe you need to append `;OLD_INFORMATION_SCHEMA=TRUE`
