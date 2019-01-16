#version 420 core

in vec2 T;
in vec2 P;

out vec4 color;

layout (binding = 0) uniform sampler2D textureMap;

uniform vec3 textColor;

uniform vec4 canvas;

void main(void) {

	if(P.x < canvas.x || P.x > canvas.x + canvas.z || P.y < canvas.y || P.y > canvas.y + canvas.w) {
		discard;
	}

	vec4 textureColor = texture(textureMap, T);
	
	if(textureColor.a < 0.25) {
		discard;
		//color = vec4(vec3(0), 1.0);
	} else {
		color = vec4(textColor, 1.0);
	}

}