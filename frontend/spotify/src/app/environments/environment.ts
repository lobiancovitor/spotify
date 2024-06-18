import { HttpHeaders } from "@angular/common/http";

export const environment = {
    apiUrlBase: "https://spotify-api-management.azure-api.net", // "http://localhost:8080",
    headers: new HttpHeaders({"Ocp-Apim-Subscription-Key":"e4f2d06040a2420d84fa6f132c0378fc"})
}
