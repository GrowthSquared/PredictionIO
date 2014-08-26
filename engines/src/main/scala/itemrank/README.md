ItemRank engine
===============

## Use case

In each day (or other time unit), there is a list of items to be shown to users and we want to personalize the order of the list of items for each user.

## Prediction Input

- user id
- list of items to be ranked

## Prediction Output

- ranked items with score


## App Data

* Users
* Items
* U2IActions
* ItemSets - the list of items at each day (or other time unit)

Import Sample Data
==================
Run under examples.
```
examples$ set -a
examples$ source ../conf/pio-env.sh
examples$ set +a

examples$ ../sbt/sbt "runMain io.prediction.engines.itemrank.CreateSampleData"
```


Run Evaluation
==============
```
../bin/pio-run  io.prediction.engines.itemrank.DetailedRunner

../bin/pio-run  io.prediction.engines.itemrank.Runner
```

Import Sample data (Obsolete)
==================

Start Mongo:

	$ mongod

At project root directory (**Imagine/**):

	$ sbt/sbt "engines/runMain io.prediction.engines.itemrank.CreateSampleData"


By default, it imports data into mongo with appid=1 and create 30 days of sample data. You may specify different appid or different number of days by using --appid and --days parameters.

For example, to import to appid=4 with 90 days of data:

	$ sbt/sbt "engines/runMain io.prediction.engines.itemrank.CreateSampleData --appid 4 --days 90"


Runner with spark-submit (Obsolete)
=========================

At project root directory (**Image/**):

		$ sbt/sbt package
		$ sbt/sbt engines/assemblyPackageDependency
		$ $SPARK_HOME/bin/spark-submit --jars engines/target/scala-2.10/engines-assembly-0.8.0-SNAPSHOT-deps.jar,/Users/ckh/dev/mac_dev/pio/Imagine/engines/target/scala-2.10/engines_2.10-0.8.0-SNAPSHOT.jar --deploy-mode "client" --class "io.prediction.engines.itemrank.Runner" core/target/scala-2.10/core_2.10-0.8.0-SNAPSHOT.jar


Run Evaluation (Obsolete)
==============

At project root directory (**Image/**):

	$ sbt/sbt package
	$ sbt/sbt engines/assemblyPackageDependency
	$ sbt/sbt "core/runMain io.prediction.tools.RegisterEngine ../engines/src/main/scala/itemrank/examples/manifest.json"

KNNAlgorithm:

	$ sbt/sbt "core/runMain io.prediction.tools.RunEvaluationWorkflow --sparkHome $SPARK_HOME io.prediction.engines.itemrank 0.8.0-SNAPSHOT --jsonDir ../engines/src/main/scala/itemrank/examples --ap kNNAlgoParams.json"

MahoutItemBasedAlgorithm:

	$ sbt/sbt "core/runMain io.prediction.tools.RunEvaluationWorkflow --sparkHome $SPARK_HOME io.prediction.engines.itemrank 0.8.0-SNAPSHOT --jsonDir ../engines/src/main/scala/itemrank/examples --ap mahoutAlgoParams.json"

RandomAlgorithm:

	$ sbt/sbt "core/runMain io.prediction.tools.RunEvaluationWorkflow --sparkHome $SPARK_HOME io.prediction.engines.itemrank 0.8.0-SNAPSHOT --jsonDir ../engines/src/main/scala/itemrank/examples --ap randomAlgoParams.json"