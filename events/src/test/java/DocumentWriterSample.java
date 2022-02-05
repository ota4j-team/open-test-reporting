import org.opentest4j.reporting.events.api.DocumentWriter;
import org.opentest4j.reporting.events.api.NamespaceRegistry;
import org.opentest4j.reporting.events.core.CoreFactory;
import org.opentest4j.reporting.events.root.Events;
import org.opentest4j.reporting.schema.Namespace;

import java.nio.file.Paths;
import java.time.Instant;

import static org.opentest4j.reporting.events.core.CoreFactory.*;
import static org.opentest4j.reporting.events.core.Result.Status.SUCCESSFUL;
import static org.opentest4j.reporting.events.root.RootFactory.finished;
import static org.opentest4j.reporting.events.root.RootFactory.started;

public class DocumentWriterSample {

    public static void main(String[] args) throws Exception {

        NamespaceRegistry namespaceRegistry = NamespaceRegistry.builder(Namespace.REPORTING_CORE) // <1>
                .add("e", Namespace.REPORTING_EVENTS) //
                .add("java", Namespace.REPORTING_JAVA) //
                .build();

        try (DocumentWriter<Events> writer = Events.createDocumentWriter(namespaceRegistry, Paths.get("events.xml"))) {
            writer.append(infrastructure(), infrastructure -> infrastructure // <2>
                    .append(userName("alice")) //
                    .append(hostName("wonderland")));
            writer.append(started("1", Instant.now(), "container")); // <3>
            writer.append(started("2", Instant.now(), "test"), started -> started.withParentId("1")); // <4>
            writer.append(finished("2", Instant.now()), finished -> finished.append(CoreFactory.result(SUCCESSFUL))); // <5>
            writer.append(finished("1", Instant.now()), finished -> finished.append(CoreFactory.result(SUCCESSFUL))); // <6>
        }
    }
}
