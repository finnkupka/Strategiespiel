#version 420 core

const int MAX_LIGHTS = 5;

in VS_OUT {

	vec2 T;

	vec3 N;
	vec3 L[MAX_LIGHTS];
	vec3 V;

} fs_in;

uniform vec3 lightColor[MAX_LIGHTS];
uniform vec3 lightAttenuation[MAX_LIGHTS];

const float shineDamper = 40;
const float reflectivity = 0.5;

out vec4 color;

layout (binding = 0) uniform sampler2D textureMap;

void main(void) {

	vec3 totalDiffuse = vec3(0.0);
	vec3 totalSpecular = vec3(0.0);

	vec3 N = normalize(fs_in.N);
	vec3 V = normalize(fs_in.V);

	for(int i = 0; i < MAX_LIGHTS; i++) {
		vec3 att = lightAttenuation[i];
		float distance = length(fs_in.L[i]);
		float attenuationFactor = att.x + (att.y * distance) + (att.z * distance * distance);

		vec3 L = normalize(fs_in.L[i]);
		vec3 R = reflect(-L, N);

		totalDiffuse += (max(dot(N, L), 0.2) * lightColor[i]) / attenuationFactor;
		totalSpecular += (pow(max(dot(R, V),0.2),shineDamper) * reflectivity * lightColor[i]) / attenuationFactor;
	}

	vec4 textureColor = texture(textureMap, fs_in.T);

	if(textureColor.a < 0.5) {
		discard;
	}

	color = textureColor * vec4(totalDiffuse, 1.0) + vec4(totalSpecular, 1.0);

}

