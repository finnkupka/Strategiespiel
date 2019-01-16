#version 420 core

layout (location = 0) in vec2 position;

out vec2 T;
out vec2 P;

uniform vec2 translation;
uniform vec2 size;

void main(void) {

	vec2 newPosition = vec2(position.x * size.x + translation.x, position.y * size.y + translation.y);
	
	gl_Position = vec4(newPosition, 0.0, 1.0);
	
	if(size.x * (16f/9f) >= size.y) {
		T = vec2(position.x * (size.x * (16f/9f) / size.y), position.y);
	} else {
		T = vec2(position.x, position.y  * (size.y / size.x * (9f/16f)));
	}
	
	//T = vec2(position.x, -position.y)*2;
	P = newPosition;
	
}