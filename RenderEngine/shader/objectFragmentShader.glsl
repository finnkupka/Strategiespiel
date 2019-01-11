#version 420 core

in vec2 T;

out vec4 color;

uniform sampler2D textureMap;

void main(void) {

	//color = vec4(1,0,1,1);
	color = texture(textureMap, T);

}
