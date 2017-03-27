import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GeoGrid {
	public String id;
	public double longtMin;
	public double longtMax;
	public double latMin;
	public double latMax;
	
	public Coordinate topLeft;
	public Coordinate topRight;
	public Coordinate bottomLeft;
	public Coordinate bottomRight;
	
	public int Counter = 0; 
	
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
		this.longtMin = grid.getAsJsonObject("properties").get("xmin").getAsDouble();
		this.longtMax = grid.getAsJsonObject("properties").get("xmax").getAsDouble();
		this.latMin = grid.getAsJsonObject("properties").get("ymin").getAsDouble();
		this.latMax = grid.getAsJsonObject("properties").get("ymax").getAsDouble();
		
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
	
	// TODO The boundary values should be identified
	public boolean isInGrid(Coordinate coord)
	{
		return 	coord.longitude >this.longtMin 
				&& coord.longitude < this.longtMax 
				&& coord.latitude > this.latMin 
				&& coord.latitude < this.latMax;
	}

}