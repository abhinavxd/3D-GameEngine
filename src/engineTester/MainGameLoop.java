package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
		Renderer renderer = new Renderer();
		
		//OpenGL Expects vertices to be defined counter clockwise by
		//default
		float[] vertices = {
				//Left bottom triangle
			    -0.5f, 0.5f, 0f,
			    -0.5f, -0.5f, 0f,
			    0.5f, -0.5f, 0f,
			    //Right top triangle
			    0.5f, -0.5f, 0f,
			    0.5f, 0.5f, 0f,
			    -0.5f, 0.5f, 0f
			  };
		
		RawModel model = loader.loadToVao(vertices);
		
	
		while(!Display.isCloseRequested()) {
		
			//game logic
			//render
			
			
			renderer.prepare();
		
			renderer.render(model);
			
			DisplayManager.updateDisplay();
			
		}
		
		//Clean up loader to delete vaos and vbos we've created
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
