package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
	
		
		//OpenGL Expects vertices to be defined counter clockwise by
		//default
//		float[] vertices = {
//				//Left bottom triangle
//			    -0.5f, 0.5f, 0f,
//			    -0.5f, -0.5f, 0f,
//			    0.5f, -0.5f, 0f,
//			    0.5f, 0.5f, 0f,		   
//			  };
		
//		int[] indices = {
//				0,1,3,  //top left triangle
//				3,1,2	// bottom right triangle
//		};
//		
//		float[] textureCoords = {
//				0,0,
//				0,1,
//				1,1,
//				1,0
//		};
//		
		

        RawModel model = OBJLoader.loadObjModel("dragon", loader);
         
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("dragonTexture")));
       
        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(10);
        texture.setReflectivity(1);
        
        Entity entity = new Entity(staticModel, new Vector3f(0,0,-50),0,0,0,1);
        
        Light light = new Light(new Vector3f(0,0,-20), new Vector3f(1,1,1));
        
        Camera camera = new Camera();
        
        MasterRenderer renderer = new MasterRenderer();
        
		while(!Display.isCloseRequested()) {
		
			//game logic
			//render
			//entity.increasePosition(0, 0,-0.1f);
			
			entity.increaseRotation(0, 1, 0);
			camera.move();
			
			renderer.processEntity(entity);
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
			
		}
		
		//Clean up loader to delete vaos and vbos we've created
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
