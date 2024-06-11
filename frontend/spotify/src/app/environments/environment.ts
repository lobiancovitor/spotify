import { HttpHeaders } from "@angular/common/http";

export const environment = {
    apiUrlBase: "https://spotify-cloud-api.azurewebsites.net", //"http://localhost:8080",
    headers: new HttpHeaders({"ocp-apim-subscription-key":"0eeca8c734a94b3d8ed377ad7a942881"})
}
