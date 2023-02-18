import com.datastax.driver.core.{Cluster, UDTValue}
import io.getquill.context.cassandra.CassandraSessionContext

// import scala.collection.JavaConverters._
import scala.jdk.CollectionConverters._

trait UdtMapper {

  this: CassandraSessionContext[_] =>

  var cluster: Cluster
  var keySpace: String

  implicit val udtDecoder: Decoder[Set[Signature]] = {
    decoder((index, row, session) => {
      row.getSet(index, classOf[UDTValue])
        .asScala
        .map(s => Signature(s.getString("signer"), s.getString("comment"), s.getLong("timestamp"))).toSet
    })
  }

  implicit val udeEncoder: Encoder[Set[Signature]] = {
    encoder((index, signatures, row, session) => {
      val sigType = cluster.getMetadata.getKeyspace(keySpace).getUserType("signature")
      val sigsUdt = signatures.map { signature =>
        // set signature data
        val uv = sigType.newValue()
        uv.setString("signer", signature.signer)
        uv.setString("comment", signature.signer)
        uv.setLong("timestamp", signature.timestamp)
        uv
      }.asJava
      row.setSet(index, sigsUdt, classOf[UDTValue])
    })
  }

}