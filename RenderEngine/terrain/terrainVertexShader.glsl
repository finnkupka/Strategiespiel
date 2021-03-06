#version 420 core

const int NUMBER_OF_LIGHTS = 5;

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 textureCoords;
layout (location = 2) in vec3 normal;

out VS_OUT {

	vec2 t;
	vec2 T;
	
	vec3 N;
	vec3 L[NUMBER_OF_LIGHTS];
	vec3 V;
	
} vs_out;

uniform mat4 transformationMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

uniform vec3 lightPosition[NUMBER_OF_LIGHTS];

uniform vec4 plane;

void main(void) {
	
	vec4 P = transformationMatrix * vec4(position, 1.0);
	vec3 N = mat3(transformationMatrix) * normal;
	
	gl_ClipDistance[0] = dot(P, plane);
	
	for(int i = 0; i < NUMBER_OF_LIGHTS; i++) {
		vs_out.L[i] = lightPosition[i] - P.xyz;
	}
	
	vec3 V = (inverse(viewMatrix) * vec4(0.0, 0.0, 0.0, 1.0)).xyz - P.xyz;

	gl_Position = projectionMatrix * viewMatrix * P;
	
	vs_out.t = textureCoords;
	vs_out.T = textureCoords * 128;
	vs_out.N = N;
	vs_out.V = V;

}
