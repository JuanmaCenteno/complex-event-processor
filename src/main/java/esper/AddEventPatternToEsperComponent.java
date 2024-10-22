package esper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.mule.DefaultMuleMessage;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.mule.api.transport.PropertyScope;

import com.espertech.esper.runtime.client.EPStatement;

/**
 * @author Juan Boubeta-Puig <juan.boubeta@uca.es>
 * @author Guadalupe Ortiz <guadalupe.ortiz@uca.es>
 * @author  Pablo Caballero Torres <pablo.caballero@uca.es>
 */
public class AddEventPatternToEsperComponent implements Callable {

	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {

		new EsperUtils();

		final Object payload = eventContext.getMessage().getPayload();

		System.out.println("*** Adding new EPL " + payload.toString());
	    final  EPStatement pattern = EsperUtils.deployNewEventPattern(payload.toString().toString()).getStatements()[0];
        
        pattern.addListener((newComplexEvents, oldComplexEvents, detectedEventPattern, epRuntime) -> {
            if (newComplexEvents != null) {
                String eventPatternName = detectedEventPattern.getEventType().getName();
                Object underlying = newComplexEvents[0].getUnderlying();
                System.out.println("** Complex event '" + eventPatternName + "' detected: " + underlying);
                
             	// Create the detected complex event as a Java map (eventPatternName, event properties)
							Map<String, Object> complexEvent = new LinkedHashMap<String, Object>();
							complexEvent.put(eventPatternName, newComplexEvents[0].getUnderlying());

							// Create the Mule message containing the complex event to be sent to the ComplexEventReceptionAndDecisionMaking flow
							MuleMessage message = new DefaultMuleMessage(complexEvent, eventContext.getMuleContext());

							// Add messageProperties, a map containing eventPatternName, to the Mule message
							Map<String, Object> messageProperties = new HashMap<String, Object>();
							messageProperties.put("eventPatternName", eventPatternName);
							message.addProperties(messageProperties, PropertyScope.OUTBOUND);

							// Send the created Mule message to the ComplexEventConsumerGlobalVM connector located in the
							// ComplexEventReceptionAndDecisionMaking flow
							try {
								eventContext.getMuleContext().getClient().dispatch("ComplexEventConsumerGlobalVM", message);
							} catch (Exception e) {
								e.printStackTrace();
							}
            }
        });

		return payload.toString();
	}
}
