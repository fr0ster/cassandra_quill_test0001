import java.util.UUID

import io.getquill._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object DocumentStore extends CassandraCluster {
  val ctx = new CassandraAsyncContext(SnakeCase, cqlSession, preparedStatementCacheSize) with UdtMapper {
    var cluster = cassandraCluster
    var keySpace = cassandraKeyspace
  }

  import ctx._

  def createDocument(document: Document): Future[Unit] = {
    val q = quote {
      query[Document].insert(lift(document))
    }
    ctx.run(q)
  }

  def updateDocument(document: Document): Future[Unit] = {
    val q = quote {
      query[Document]
        .filter(p => p.company == lift(document.company) && p.id == lift(document.id))
        .update(_.name -> lift(document.name), _.status -> lift(document.status))
    }
    ctx.run(q)
  }

  def searchDocuments(company: String, id: UUID): Future[List[Document]] = {
    val q = quote {
      query[Document].filter(p => p.company == lift(company) && p.id == lift(id))
    }
    ctx.run(q)
  }

}