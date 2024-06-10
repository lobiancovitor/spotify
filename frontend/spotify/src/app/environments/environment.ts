import { HttpHeaders } from "@angular/common/http";

export const environment = {
    apiUrlBase: "https://spotify-cloud-api.azurewebsites.net/",
    headers: new HttpHeaders({"ocp-apim-subscription-key":"dcae0f4c6d964dd88fb433a21f48ea24"})
}