
/*
 * Copyright 2021-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
