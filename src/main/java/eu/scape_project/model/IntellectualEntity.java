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
package eu.scape_project.model;

import eu.scape_project.util.CopyUtil;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
*
* @author frank asseg
*
*/
@XmlRootElement(name = "entity", namespace = "http://scape-project.eu/model")
public class IntellectualEntity extends Identified {

    private final int versionNumber;
    private final List<Identifier> alternativeIdentifiers;
    private final Object descriptive;
    private final List<Representation> representations;

    private final LifecycleState lifeCycleState;

    @SuppressWarnings("unused")
    private IntellectualEntity() {
        super(null);
        this.alternativeIdentifiers = null;
        this.descriptive = null;
        this.representations = null;
        this.lifeCycleState = null;
        this.versionNumber = 1;
    }

    public IntellectualEntity(Builder builder) {
        super(builder.identifier);
        this.alternativeIdentifiers = builder.alternativeIdentifiers;
        this.descriptive = builder.descriptive;
        this.representations = builder.representations;
        this.lifeCycleState = builder.lifecycleState;
        this.versionNumber = builder.versionNumber;
    }

    public List<Identifier> getAlternativeIdentifiers() {
        return alternativeIdentifiers;
    }

    public Object getDescriptive() {
        return descriptive;
    }

    public LifecycleState getLifecycleState() {
        return lifeCycleState;
    }

    public List<Representation> getRepresentations() {
        return representations;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    @Override
    public String toString() {
        return "IntellectualEntity [identifier=" + identifier
                + ", versionNumber=" + versionNumber
                + ", alternativeIdentifiers=" + alternativeIdentifiers
                + ", descriptive=" + descriptive
                + ", representations=" + representations
                + ", lifeCycleState=" + lifeCycleState
                + "]";
    }



    public static class Builder {
        private Identifier identifier;
        private int versionNumber;
        private List<Identifier> alternativeIdentifiers;
        private Object descriptive;
        private List<Representation> representations;
        private LifecycleState lifecycleState;

        public Builder() {
            super();
        }

        public Builder(IntellectualEntity orig){
            IntellectualEntity copy = CopyUtil.deepCopy(IntellectualEntity.class, orig);
            this.identifier = copy.identifier;
            this.descriptive = copy.descriptive;
            this.versionNumber = copy.versionNumber;
            this.alternativeIdentifiers = copy.alternativeIdentifiers;
            this.representations = copy.representations;
            this.lifecycleState = copy.lifeCycleState;
        }

        public Builder alternativeIdentifiers(List<Identifier> alternativeIdentifiers) {
            this.alternativeIdentifiers = alternativeIdentifiers;
            return this;
        }

        public IntellectualEntity build() {
            versionNumber = (versionNumber == 0) ? 1 : versionNumber;
            return new IntellectualEntity(this);
        }

        public Builder descriptive(Object descriptive) {
            this.descriptive = descriptive;
            return this;
        }

        public Builder identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder lifecycleState(LifecycleState state) {
            this.lifecycleState = state;
            return this;
        }

        public Builder representations(List<Representation> representations) {
            this.representations = representations;
            return this;
        }

        public Builder versionNumber(int versionNumber) {
            this.versionNumber = versionNumber;
            return this;
        }

    }

}