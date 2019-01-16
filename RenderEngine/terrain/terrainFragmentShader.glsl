#version 420 core

const int NUMBER_OF_LIGHTS = 5;

in VS_OUT {

	vec2 t;
	vec2 T;
	
	vec3 N;
	vec3 L[NUMBER_OF_LIGHTS];
	vec3 V;

} fs_in;

uniform vec3 lightColor[NUMBER_OF_LIGHTS];
uniform vec3 attenuation[NUMBER_OF_LIGHTS];

const float shineDamper = 40;
const float reflectivity = 0.5;

out vec4 color;

layout (binding = 0) uniform sampler2D textureMap1;
layout (binding = 1) uniform sampler2D textureMap2;
layout (binding = 2) uniform sampler2D textureMap3;
layout (binding = 3) uniform sampler2D textureMap4;

layout (binding = 4) uniform sampler2D blendMap;

void main(void) {

	vec3 totalDiffuse = vec3(0.0);
	vec3 totalSpecular = vec3(0.0);

	vec3 N = normalize(fs_in.N);
	vec3 V = normalize(fs_in.V);
	
	for(int i = 0; i < NUMBER_OF_LIGHTS; i++) {
		vec3 att = attenuation[i];
		float distance = length(fs_in.L[i]);
		float attenuationFactor = att.x + (att.y * distance) + (att.z * distance * distance);
		
		vec3 L = normalize(fs_in.L[i]);
		vec3 R = reflect(-L, N);
		
		totalDiffuse += (max(dot(N, L), 0.2) * lightColor[i]) / attenuationFactor;
		totalSpecular += (pow(max(dot(R, V),0.2),shineDamper) * reflectivity * lightColor[i]) / attenuationFactor;
	}

	vec3 blendMapColor = texture(blendMap, fs_in.t).rgb;

	vec4 color1 = texture(textureMap1, fs_in.T) * (1 - (blendMapColor.r + blendMapColor.g + blendMapColor.b));
	vec4 color2 = texture(textureMap2, fs_in.T) * blendMapColor.r;
	vec4 color3 = texture(textureMap3, fs_in.T) * blendMapColor.g;
	vec4 color4 = texture(textureMap4, fs_in.T) * blendMapColor.b;

	vec4 textureColor = color1 + color2 + color3 + color4;
	
	color = textureColor * vec4(totalDiffuse, 1.0) + vec4(totalSpecular, 1.0);

}
