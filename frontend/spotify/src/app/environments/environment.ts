import { HttpHeaders } from "@angular/common/http";

export const environment = {
    apiUrlBase: "https://spotify-api-management.azure-api.net", // "http://localhost:8080",
    headers: new HttpHeaders({"Access-Control-Allow-Origin":"e4f2d06040a2420d84fa6f132c0378fc"})
} // Ocp-Apim-Subscription-Key