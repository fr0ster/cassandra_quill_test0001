import java.util.{Date, UUID}

case class Signature(signer: String, comment: String, timestamp: Long)

case class Document(id: UUID, company: String, name: String, tags: Set[String], signatures: Set[Signature],
                    status: String, timestamp: Date = new Date)