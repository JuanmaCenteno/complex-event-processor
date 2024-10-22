package esper;



import org.json.JSONObject;
import org.mule.api.annotations.param.Payload;


/**
 * @author Juan Boubeta-Puig <juan.boubeta@uca.es>
 * @author Guadalupe Ortiz <guadalupe.ortiz@uca.es>
 * @author  Pablo Caballero Torres <pablo.caballero@uca.es>
 */

public class SendEventToEsperComponent {

	@SuppressWarnings("unchecked")
	public synchronized void sendEventToEsper(@Payload String eventReceived) {

			JSONObject myJSONMessage = new JSONObject(eventReceived);
            if (myJSONMessage.has("eventTypeName")){
            	String eventTypeName = myJSONMessage.getString("eventTypeName");
            	if (eventTypeName!=null) {
            	System.out.println ("TIPO: "+ eventTypeName);
            	EsperUtils.sendEventTyped(eventReceived, eventTypeName);
                }
            }	                       

	}
	
}
