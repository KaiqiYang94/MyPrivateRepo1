import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GeoGrid {
	public String id;
	public double xmin;
	public double xmax;
	public double ymin;
	public double ymax;
	
	public Coordinate topLeft;
	public Coordinate topRight;
	public Coordinate bottomLeft;
	public Coordinate bottomRight;
	
	public GeoGrid() {
		topLeft = new Coordinate();
		topRight = new Coordinate();
		bottomLeft = new Coordinate();
		bottomRight = new Coordinate();
	}
	
	public GeoGrid(JsonElement jele ) {
		topLeft = new Coordinate();
		topRight = new Coordinate();
		bottomLeft = new Coordinate();
		bottomRight = new Coordinate();
		
		JsonObject grid = jele.getAsJsonObject();

		this.id = grid.getAsJsonObject("properties").get("id").getAsString();
		this.xmin = grid.getAsJsonObject("properties").get("xmin").getAsDouble();
		this.xmax = grid.getAsJsonObject("properties").get("xmax").getAsDouble();
		this.ymin = grid.getAsJsonObject("properties").get("ymin").getAsDouble();
		this.ymax = grid.getAsJsonObject("properties").get("ymax").getAsDouble();
		
		JsonArray geoData = grid.getAsJsonObject("geometry").getAsJsonArray("coordinates").get(0).getAsJsonArray();
		
		this.topLeft.longitude = geoData.get(0).getAsJsonArray().get(0).getAsJsonPrimitive().getAsDouble();
		this.topLeft.latitude = geoData.get(0).getAsJsonArray().get(1).getAsJsonPrimitive().getAsDouble();
		
		this.topRight.longitude = geoData.get(1).getAsJsonArray().get(0).getAsJsonPrimitive().getAsDouble();
		this.topRight.latitude = geoData.get(1).getAsJsonArray().get(1).getAsJsonPrimitive().getAsDouble();
		
		this.bottomRight.longitude = geoData.get(2).getAsJsonArray().get(0).getAsJsonPrimitive().getAsDouble();
		this.bottomRight.latitude = geoData.get(2).getAsJsonArray().get(1).getAsJsonPrimitive().getAsDouble();
		
		this.bottomLeft.longitude = geoData.get(3).getAsJsonArray().get(0).getAsJsonPrimitive().getAsDouble();
		this.bottomLeft.latitude = geoData.get(3).getAsJsonArray().get(1).getAsJsonPrimitive().getAsDouble();
		
	}

}