import com.datastax.driver.core.utils.UUIDs

import scala.concurrent.Await
import scala.concurrent.duration._

object Main extends App {

  val id = UUIDs.timeBased()
  val signatures = Set(Signature("eranga", "sign document", System.currentTimeMillis()))
  val document = Document(id, "rahasak", "lambda", Set("dev", "ops"), signatures, "pending")

  // create document
  Await.result(DocumentStore.createDocument(document), 10.seconds)

  /*
   * execute insert query below
   *  INSERT INTO document (id,company,name,tags,signatures,status,timestamp) VALUES (?, ?, ?, ?, ?, ?, ?)
   */

  // search document
  val docs = Await.result(DocumentStore.searchDocuments("rahasak", id), 10.seconds)
  println(docs)

  /*
   * execute search query below
   *  SELECT id, company, name, tags, signatures, status, timestamp FROM document WHERE company = ? AND id = ?
   *
   * output
   *  List(Document(5fe2a3f0-ebd9-11e9-af14-6177c1a8fcc4,rahasak,lambda,Set(ops, dev),Set(Signature(eranga,eranga,1570765446116)),pending,Thu Oct 10 23:44:06 EDT 2019))
   */

  // update document
  Await.result(DocumentStore.updateDocument(Document(id, "rahasak", "lambdaops", Set(), Set(), "approved")), 10.seconds)

  /*
   * execute update query below
   *  UPDATE document SET name = ?, status = ? WHERE company = ? AND id = ?
   */

}