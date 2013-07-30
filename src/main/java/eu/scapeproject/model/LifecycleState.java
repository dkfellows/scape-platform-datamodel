/*
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package eu.scapeproject.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
*
* @author frank asseg
*
*/
@XmlRootElement
public class LifecycleState {


	@XmlValue
    private final String details;
    @XmlAttribute(name="lifecyclestate")
    private final State state;

    @SuppressWarnings("unused")
    private LifecycleState(){
        this.details=null;
        this.state=null;
    }

    public LifecycleState(String details, State state) {
        super();
        this.details = details;
        this.state = state;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LifecycleState other = (LifecycleState) obj;
        if (details == null) {
            if (other.details != null)
                return false;
        } else if (!details.equals(other.details))
            return false;
        if (state != other.state)
            return false;
        return true;
    }

    public String getDetails() {
        return details;
    }

    public State getState() {
        return state;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((details == null) ? 0 : details.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        return result;
    }

    @Override
   	public String toString() {
   		return "LifecycleState [details=" + details + ", state=" + state + "]";
   	}

    public enum State {
        INGESTED, INGESTING, INGEST_FAILED, OTHER, NEW;
    }

}