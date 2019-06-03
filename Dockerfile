FROM hseeberger/scala-sbt
WORKDIR /SchachMSM
ADD . /SchachMSM
CMD sbt run
