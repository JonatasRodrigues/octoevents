package br.com.jaya.jonatasrodrigues.repositorio

import br.com.jaya.jonatasrodrigues.domain.Event
import br.com.jaya.jonatasrodrigues.domain.Issue
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class IssueRepositoryImpl : IssueRepository{

    object EventTable : Table() {
        val action = varchar("action", 50)
        val issue = long("issueId")

        fun toDomain(row: ResultRow, issue: Issue): Event {
            return Event(
                action = row[EventTable.action],
                issue = issue
            )
        }
    }

    object IssueTable : Table() {
        val id = long("id").primaryKey()
        val url = varchar("url", 250)
        val number = long("number")
        val created_at = varchar("created_at", length = 20)

        fun toDomain(row: ResultRow): Issue {
            return Issue(
                id = row[IssueTable.id],
                url = row[IssueTable.url],
                created_at = row[IssueTable.created_at],
                number = row[IssueTable.number]
             )
        }
    }

    override fun save(event: Event) = transaction {
        SchemaUtils.drop(EventTable)
        SchemaUtils.drop(IssueTable)

        SchemaUtils.create(EventTable)
        SchemaUtils.create(IssueTable)

         saveIssue(event.issue)

        EventTable.insert { row ->
            row[action] = event.action
            row[issue] = event.issue.id
        }


    }

   private fun saveIssue(issue: Issue) {
       transaction {
           IssueTable.insert { row ->
               row[id] = issue.id
               row[url] = issue.url
               row[number] = issue.number
               row[created_at] = issue.created_at
           }
       }
   }

    override fun getEvent(issueNumber:Long) = transaction {
            EventTable.join(IssueTable, JoinType.INNER, additionalConstraint = { EventTable.issue eq IssueTable.id })
                    .select { IssueTable.number eq issueNumber }
                    .map { EventTable.toDomain(it, IssueTable.toDomain(it)) }
        }
    }


