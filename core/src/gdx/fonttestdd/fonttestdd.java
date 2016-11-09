//Luke Simpson
package gdx.fonttestdd;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class fonttestdd extends ApplicationAdapter {

    SpriteBatch batch;
    TextureAtlas buttonAtlas;
    TextButton.TextButtonStyle buttonStyle;
    TextButton button;
    Skin skin;
    BitmapFont textFont, textFontSmall, textFontSmallest;
    Stage stage;

    private void createFonts() {
        //True type font is imported
        FileHandle fontFile = Gdx.files.internal("Woods.ttf");
        // A Generator to create the font, can be named anything
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        // a Parameter setter for the generator can be name anything
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        //enter the text parameters you wish to use
        parameter.size = 25;
        parameter.color = Color.BLACK;
        // tells the generator to create a font with the parameters that you set
        textFont = generator.generateFont(parameter);
        // to create a new font simply enter new parameters, it seems only the parameter
        // you wish to change will have to be re-entered for example here the text stays black
        // while the size is being change
        parameter.size = 18;
        textFontSmall = generator.generateFont(parameter);
        parameter.size = 10;
        parameter.color = Color.CORAL;
        parameter.shadowColor = Color.LIGHT_GRAY;
        parameter.shadowOffsetX = 5;
        parameter.shadowOffsetY = 2;
        textFontSmallest = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void create() {
        createFonts();
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("Button.pack"));
        skin.addRegions(buttonAtlas);
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = skin.getDrawable("buttonpressed01");
        buttonStyle.down = skin.getDrawable("buttonpressed02");
        buttonStyle.font = textFont;
        button = new TextButton("Level Select", buttonStyle);
        button.setSize(300, 100);
        button.setPosition(100, 100);
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Wow Wee, You clicked a Button  !");

                return true;
            }
        });
        stage.addActor(button);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		textFontSmall.draw(batch,"Level One",100,100);
		textFontSmallest.draw(batch,"Level two",100,75);
		batch.end();
		stage.draw();
    }
}
