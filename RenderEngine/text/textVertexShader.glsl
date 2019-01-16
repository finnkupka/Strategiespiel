#version 420 core

layout (location = 0) in vec2 position;
layout (location = 1) in vec2 textureCoords;

out vec2 T;
out vec2 P;

uniform vec2 translation;
uniform float size;

void main(void) {

	vec2 newPosition = position * size + translation;

	gl_Position = vec4(newPosition, 0.0, 1.0);
	
	T = textureCoords;
	P = newPosition;

}