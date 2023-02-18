import akka.event.slf4j.SLF4JLogging
import com.datastax.driver.core.{Cluster, _}
import com.datastax.oss.driver.api.core.CqlSession

trait CassandraCluster extends CassandraConf with SLF4JLogging {
  lazy val poolingOptions: PoolingOptions = {
    new PoolingOptions()
      .setConnectionsPerHost(HostDistance.LOCAL, 4, 10)
      .setConnectionsPerHost(HostDistance.REMOTE, 2, 4)
  }

  lazy val cassandraCluster: Cluster = {
    val builder = Cluster.builder()
    for (cp <- cassandraHosts) builder.addContactPoint(cp)
    builder.withPort(cassandraPort)
    builder.withPoolingOptions(poolingOptions)

    builder.build()
  }

  lazy implicit val session: Session = cassandraCluster.connect()

  lazy implicit val cqlSession: CqlSession = CqlSession.builder().withKeyspace(cassandraKeyspace).build()
}