package br.unb.cic.poo.libgdx

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class GameOfLifeS extends ApplicationAdapter {
  private[libgdx] var batch : SpriteBatch = _
  private[libgdx] var img : Texture = _

  override def create(): Unit = {
    batch = new SpriteBatch
    //img = new Texture("badlogic.jpg")
  }

  override def render(): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    //batch.draw(img, 160, 120)
    batch.end()
  }

  override def dispose(): Unit = {
    batch.dispose()
    //img.dispose()
  }
}