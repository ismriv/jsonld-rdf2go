JSON-LD RDF2Go
==============

This is an extension of the JSON-LD library for Java (http://github.com/tristan/jsonld-java) which adds support for RDF2Go (http://semanticweb.org/wiki/RDF2Go).

Setup
-----

Add the following repository in the pom.xml of your Maven project:

    <repository>
        <id>smile-maven2-snapshot-repository</id>
        <name>Smile Maven2 Snapshot Repository</name>
        <url>http://resources.smile.deri.ie/m2-snapshots/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
            <checksumPolicy>fail</checksumPolicy>
        </snapshots>
    </repository>

Add the following dependency in the pom.xml:

    <dependency>
        <groupId>ie.deri.smile</groupId>
        <artifactId>jsonld-rdf2go</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>

Examples
--------

This project adds 2 new classes: RDF2GoTripleCallback and RDF2GoJSONLDSerializer. They add the connectors to add JSON-LD data to an RDF2Go model, and to serialize an RDF2Go model into a JSON-LD document.

The examples provided for Sesame and Jena at https://github.com/tristan/jsonld-java/blob/master/README.md#code-example are also applicable for this extension.
