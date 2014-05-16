package com.example.holamundo;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;

//import com.example.holamundo.R;
//import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends BaseActivity {
	
	private Fragment mContent;
	
	public MainActivity() {
		super(R.string.app_name);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// estableciendo el disenio(vista) de este Activity (Lo que normalmente se tiene que hacer siempre)
		//pero solo toma el disenio como un objeto de manera general ya que no esta siendo invocado por su ID
		setContentView(R.layout.content_frame);
				
		//Fragment de detalle (Lado Derecho)
		//estableciendo un fragment por defecto
		//se le pasa el Id del Disenio y el Fragment que se ejecutara sobre el
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame,new OptionsFragment()).commit();
		
		//Estableciendo el fragment del Menu Principal y para esto se hace lo siguiente
		
		//este es un metodo especial de SM que establece la vista que ira detras de la vista
		//explicita y le enviamos una determinada vista como parametro
		setBehindContentView(R.layout.menu_frame);
		
		getSupportFragmentManager().beginTransaction()
		
		//creacion de la lista del Menu Principal, aqui es donde se rellena la lista
		.replace(R.id.menu_frame, new MainListFragment())//menu principal
		.commit();
		
		// customize the SlidingMenu
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}
	
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, fragment)
		.commit();
		getSlidingMenu().showContent();
	}

}
