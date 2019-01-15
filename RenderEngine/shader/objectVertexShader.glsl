#version 420 core

const int MAX_LIGHTS = 5;

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 textureCoords;
layout (location = 2) in vec3 normal;

out VS_OUT {

	vec2 T;

	vec3 N;
	vec3 L[MAX_LIGHTS];
	vec3 V;

} vs_out;

uniform vec3 lightPosition[MAX_LIGHTS];

uniform mat4 transformationMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

void main(void) {

	vec4 P = transformationMatrix * vec4(position, 1.0);

	for(int i = 0; i < MAX_LIGHTS; i++) {
		vs_out.L[i] = lightPosition[i] - P.xyz;
	}

	vec3 V = (inverse(viewMatrix) * vec4(0.0, 0.0, 0.0, 1.0)).xyz - P.xyz;

	gl_Position = projectionMatrix * viewMatrix * P;

	vs_out.T = textureCoords;
	vs_out.N = mat3(transformationMatrix) * normal;
	vs_out.V = V;

}
