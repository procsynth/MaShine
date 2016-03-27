/** 
 *  A frame from states of features
 *
 *	@author procsynth - Antoine Pintout
 *	@since  13-02-2016`
 */

package mashine.scene;

import mashine.*;
import mashine.scene.*;
import mashine.scene.features.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.Serializable;

public class Frame implements Serializable{

	private static final long serialVersionUID = 0xF44E0001L;

	private HashMap<String,EditableFeature> features;

	public Frame(ArrayList<Device> devices){

		features = new HashMap<String,EditableFeature>();

		for(Device d : devices){
			ArrayList<Feature> devFeatures = d.getFeatures();
			for(Feature f : devFeatures){
				if(f instanceof EditableFeature){
					features.put(d.getIdentifier() +"."+ f.getType(), (EditableFeature) Feature.cloneFeature(f));
				}
			}
		}

	}

	public Frame(Frame frame){

		features = new HashMap<String,EditableFeature>();

		HashMap<String,EditableFeature> frameFeatures = frame.getFeatures();	

		for(String featureIdentifier : frameFeatures.keySet()){
			features.put(featureIdentifier, (EditableFeature) Feature.cloneFeature(frameFeatures.get(featureIdentifier)));
		}
	}

	public Frame(){
		features = new HashMap<String,EditableFeature>();
	}

	public HashMap<String,EditableFeature> getFeatures(){
		return features;
	}

	public EditableFeature getFeature(Device d, Feature f){
		if(features.containsKey(d.getIdentifier()+"."+f.getType())){
			return features.get(d.getIdentifier()+"."+f.getType());
		}else{
			return null;
		}
	}

	public void addFeature(Device d, EditableFeature f){
		features.put(d.getIdentifier()+"."+f.getType(), (EditableFeature) Feature.cloneFeature(f));
	}
	public void removeFeature(Device d, EditableFeature f){
		features.remove(d.getIdentifier()+"."+f.getType());
	}

	public void updateFeature(Device d, String featureType, String fieldName, int value){
		if(features.containsKey(d.getIdentifier()+"."+featureType))
			features.get(d.getIdentifier()+"."+featureType).setField(fieldName, value);
	}
}