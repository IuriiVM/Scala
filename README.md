# Scala


## REQUIREMENTS

Oracle JDK, the Java Development Kit, version 1.8
Scala Build Tool (sbt), a build tool for Scala, version 0.13.x, or newer
The Scala IDE for Eclipse, Intellij IDEA or another IDE of your choice

## TIPS

Run SBT as Administrator!

Launch the Scala REPL (the interactive Scala console) through sbt

build.sbt is the root project file

## NOTES

Databricks Community Edition - ability to play with Spark in Web without needing to setup
Meaning of .reduce operation? Why it can return non-deterministic results?
RDD - Resilient Distributed Dataset
Specific distributed computing issues:
Partial failure of distributed computation on subset of nodes
Latency: some operations take longer because of network communications

Hadoop success is simple API and fault tolerance. Fault tolerance comes at a cost of additional operations of data copying between nodes (slow network operations are involved)
Spark is also good in fault tolerance, but uses other approach to handle network latency and it is significantly reduced: data is almost immutable, but what is repeated in the case of failures is functional data transformations (performed in memory, so very fast). Intermediate results are not saved to disk.
Spark can be 100x times more performant as Hadoop

RDDs are close to Scala immutable sequential or parallel collections (similar API: map, flatMap, filter, reduce, fold, aggregate)
flatMap allows you to apply some operation to all elements of collection and aggregate results together (like split collection of strings by words and return their resulting list)
map allows to map elements of collection with something else: (word, 1) to calculate number of words later
reduceByKey - to be investigated
RDD can be created by:
* Transforming of existing RDD
* From a SparkContext or SparkSession object which represent handle to the Spark cluster, connection between it and your application. For example its textFile method reads text file from HDFS and return RDD of strings. Method parallelize converts existing Scala collection to an RDD.

Transformer in Scala: operation which returns new collection as a result (not single value). Examples: map, filter, flatMap, groupBy
Accessor in Scala returns single value as a result: reduce, fold, aggregate.
In Spark we have transformations (returns RDD) and actions (returns or saves in HDFS single value based on RDD passed) instead.
Transformations are lazy (not computed immediately), actions are eager (computed immediately).
Laziness helps to deal with network latency in Spark.
Actions:
* collect() returns all elements from RDD
* take(num: Int) returns first num elements from RDD
* foreach(f) apples function f to all elements of RDD
* reduce(op: (A, A) => A) combines elements in RDD together using op function and return result
* count() - number of elements in RDD

Transformations which combine RDDs:
* union(other: RDD[T])
* intersection (other: RDD[T]) - returns RDD containing elements present in both source RDDs
* subtract (other: RDD[T]) - first RDD minus second RDD
* cartesian[U](other: RDD[U]): RDD[(T, U)] - Cartesian product with the other RDD  

Spark actions absent in Scala:
* takeSample - returns array of N random elements from RDD
* takeOrdered - first N elements in natural order or using comparator
* saveAsTextFile - writes elements of dataset to the text file
* saveAsSequenceFile -  writes elements of dataset as a Hadoop Sequence file
If you need to cache some RDD to reuse it in multiple places without reevaluation you can use persist() or cache() to cache RDD in memory. cache() is a shorthand which uses in memory approach as regular Java objects. persist() allows you to choose method of persistence (memory, disk, serialized etc).

Spark jobs are usually executed in a shape when we have one node with driver program (Spark context) and set of worker nodes. These nodes communicate between each other via a cluster manager (for example, YARN or Mesos). Worker nodes (or Executors) is the place providing in memory storage for cached RDDs.
It is important to understand where (on driver or executor) each particular line of code will execute.

