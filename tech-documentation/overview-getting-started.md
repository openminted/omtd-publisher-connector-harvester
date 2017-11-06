# Overview

This document highlights the basic requirements and how the system works.

TODO: this is a rough outline and needs generalising. It may also be out of date.

## Dependencies
The dependencies for running the project are
RabbitMQ
MySQL/Mariadb

The system is designed to work on \*nix. Windows systems are not currently tested.


### RabbitMQ
Open the file:
[project-root]/Queue/src/main/resources/queue.properties

You will want to uncomment the section

##localhost
#QUEUE_HOST = localhost
#QUEUE_PORT = 5672

Set this to the location of your RabbitMQ cluster


### Database
Open the file
[project-root]/Queue/src/main/resources/database.properties

Change the bonecp.url, username and password values accordingly.

Please note that these configuration files are under source control so be careful not to commit your changes.


### Filesystem

To create the relevant folders, run the following (Stolen from here):

mkdir -p /data/dit/data/elsevier/sitemaps-storage/
mkdir -p /data/dit/data/elsevier/metadata/
mkdir -p /data/dit/data/elsevier/pdf
mkdir -p /data/dit/data/elsevier/crawler-storage
sudo chmod -R 777 /data/dit/data/elsevier/


## Running the process
To run a process, NetBeans should run the service automatically. To run from a command line, use the following

java –jar [root]/[project-name]/target/[project-name]-shaded.jar


## Lifecycle overview
There are 3 main components of the Publisher Connector

Harvester
Retriever
Processor


### Harvester
This iterates over an API retrieving data about each item.


In the PubMed Central Harvester, we iterate over an API endpoint retrieving a compressed file which we push the link into a queue (https://www.ncbi.nlm.nih.gov/pmc/utils/oa/oa.fcgi?from=2004-10-05%2012:49:55).

It is submitted into a queue using (PMCHarvester.java):
queueService.scheduleArticleToDedicatedQueue(this.queueName, article);

For an example, see PMCHarvester.java:111. As we only get a URL which is a gzip file, we submit the gzip file URL to the Queue.

For the WileyHarvester, we iterate over the metadata directly. For this use case, we insert the metadata into the queue immediately.
See WileyHarvester.java:125.


### Retriever
The Retriever subscribes to the queue registered by Harvester.

For PubMed, we download the gzip item using the URL provided to us in the Harvester. The downloaded file is saved into a temporary directory and the location is submitted to another queue.

I didn’t implement this stage for Wiley.

### Processor
This is where we finally store and/or retrieve the document from the publisher.


### Final Result
The final result is that for each item, 2 files are created:

/data/dit/data/[publisher]/pdf/[random-id]/[base64-encoded-url-of-paper].pdf
/data/dit/data/[publisher]/json/[random-id]/[base64-encoded-url-of-paper].json

We have another service that crawls the PDF folder and for every file that exists, if a JSON file can also be found, then it is published online via ResourceSync
