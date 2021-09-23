# Geocoder


## Тестовое задание 

https://myte.me/tasks/RtngmS04tLWEPKTfvUd0

## Задача
Необходимо разработать приложение с возможностью прямого и обратного геокодирования (из координат в адрес и наоборот). Не требуется разворачивать собственную базу данных с адресами, достаточно использовать сторонние API (Google, Яндекс, другое). Таким образом приложение является кэширующим прокси-сервисом для стороннего сервиса геокодирования. Приложение должно предоставлять API с JSON форматом данных.

#### Spring Boot

Кэширование результатов запросов. В качестве кэша можно использовать SQL базу данных (MySQL, Postgres, другое), либо NoSQL решение (Memcached, Redis, другое).

Логирование ошибок и работы приложения в целом.

Покрытие тестами базовой функциональности.

Дополнительно (будет преимуществом) Метрики работы приложения (Actuator, Dropwizard, другое).

Сборка и запуск приложения в Docker.

## Решение

Создание зеркального прокси сервера на базе открытого API graphhopper.com

### Стек технологий
Spring Boot v 2.5.4
Feign Client
Lombok
Hazelcast
Swagger
Actuator

#### Описание
Развертывание происходит на порту 8080

Swagger доступен по - /geocoder/swagger-ui.html

Actuator доступен по - /geocoder/my_actuator

Основной URL доступен по - /geocoder/api/v1/get_address

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">    
</head>
<body>
<body>
<div class="sc-AxhCb iWvlZG"><h2 class="sc-fzozJi dSJzNl"><a class="sc-fznyAO CWQMf" href="#operation/getGeocode"></a>Geocoding
    Endpoint </h2>
    <div class="sc-pBzUF kPMtEj">
        <div class="sc-fzoant sc-fzoYHE bmoWQk"><h3 id="introduction">Introduction</h3>
            <p><em>Geocoding</em> describes the process of transforming an textual address representation to a
                coordinate (<code>latitude,longitude</code>).
                For example the conversion from <code>Berlin</code> to <code>52.5170365,13.3888599</code>.</p>
            <p><em>Reverse geocoding</em> converts a coordinate to a textual address representation or place name. Find
                out more about Geocoding itself on <a href="http://en.wikipedia.org/wiki/Geocoding">Wikipedia</a>.</p>
        </div>
    </div>
    <div class="sc-oTBUA hXGkGv">
        <div class="sc-pJkiN kSIOTQ"><h5 class="sc-fzplWN sc-pZaHX JNCDo">Authorizations: </h5></div>
        <div class="sc-pReKu iodlDJ"><span class="sc-pBolk fYYYgC"><span class="sc-qYSYK gVmsPZ"><a href="#section/Authentication/api_key">api_key</a></span></span></div>
<p><em>For testng can use api-key: 3048cc9a-b274-4045-9a96-8fd342e8a86f</em></p>
    </div>
    <div><h5 class="sc-fzplWN kfkaqq">query Parameters</h5>
        <table class="sc-fznxsB hSfQgO">
            <tbody>
            <tr>
                <td class="sc-fzqBZW sc-fzoyAV hjJCfv" kind="field" title="q"><span class="sc-fzoXzr iIvtPV"></span>q
                </td>
                <td data-section-id="operation/getGeocode/q" class="sc-fzoLag cxieCN">
                    <div>
                        <div><span class="sc-fzqzlV sc-fzqLLg dxWAkt"></span><span class="sc-fzqzlV sc-fzoxnE dpLlWm">string</span>
                        </div>
                        <div>
                            <div class="sc-fzoant sc-fzoYHE hanQOS"><p>If you do forward geocoding, this is <code>required</code>
                                and is a textual description of the address you are looking for.</p>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="sc-fzqBZW sc-fzoyAV hjJCfv" kind="field" title="locale"><span class="sc-fzoXzr iIvtPV"></span>locale
                </td>
                <td data-section-id="operation/getGeocode/locale" class="sc-fzoLag cxieCN">
                    <div>
                        <div><span class="sc-fzqzlV sc-fzqLLg dxWAkt"></span><span class="sc-fzqzlV sc-fzoxnE dpLlWm">string</span>
                        </div>
                        <div><span class="sc-fzqzlV jDREaU"> Default: </span> <span class="sc-fzqzlV sc-fzonjX iwWBsD">"en"</span>
                        </div>
                        <div>
                            <div class="sc-fzoant sc-fzoYHE hanQOS"><p>Display the search results for the specified
                                locale. Currently French (fr), English (en), German (de) and Italian (it) are supported.
                                If the locale wasn't found the default (en) is used.</p>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="sc-fzqBZW sc-fzoyAV hjJCfv" kind="field" title="limit"><span class="sc-fzoXzr iIvtPV"></span>limit
                </td>
                <td data-section-id="operation/getGeocode/limit" class="sc-fzoLag cxieCN">
                    <div>
                        <div><span class="sc-fzqzlV sc-fzqLLg dxWAkt"></span><span class="sc-fzqzlV sc-fzoxnE dpLlWm">integer</span><span class="sc-fzqzlV sc-fzoxnE dpLlWm"> &lt;int32&gt; </span></div>
                        <div><span class="sc-fzqzlV jDREaU"> Default: </span> <span class="sc-fzqzlV sc-fzonjX iwWBsD">10</span>
                        </div>
                        <div>
                            <div class="sc-fzoant sc-fzoYHE hanQOS"><p>Specify the maximum number of results to
                                return</p>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="sc-fzqBZW sc-fzoyAV hjJCfv" kind="field" title="reverse"><span class="sc-fzoXzr iIvtPV"></span>reverse
                </td>
                <td data-section-id="operation/getGeocode/reverse" class="sc-fzoLag cxieCN">
                    <div>
                        <div><span class="sc-fzqzlV sc-fzqLLg dxWAkt"></span><span class="sc-fzqzlV sc-fzoxnE dpLlWm">boolean</span>
                        </div>
                        <div><span class="sc-fzqzlV jDREaU"> Default: </span> <span class="sc-fzqzlV sc-fzonjX iwWBsD">false</span>
                        </div>
                        <div>
                            <div class="sc-fzoant sc-fzoYHE hanQOS"><p>It is <code>required</code> to be
                                <code>true</code> if you want to do a reverse geocoding request. If it is
                                <code>true</code>, <code>point</code> must be defined as well, and <code>q</code> must
                                not be used.</p>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="sc-fzqBZW sc-fzoyAV hjJCfv" kind="field" title="debug"><span class="sc-fzoXzr iIvtPV"></span>debug
                </td>
                <td data-section-id="operation/getGeocode/debug" class="sc-fzoLag cxieCN">
                    <div>
                        <div><span class="sc-fzqzlV sc-fzqLLg dxWAkt"></span><span class="sc-fzqzlV sc-fzoxnE dpLlWm">boolean</span>
                        </div>
                        <div><span class="sc-fzqzlV jDREaU"> Default: </span> <span class="sc-fzqzlV sc-fzonjX iwWBsD">false</span>
                        </div>
                        <div>
                            <div class="sc-fzoant sc-fzoYHE hanQOS"><p>If <code>true</code>, the output will be
                                formatted.</p>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="sc-fzqBZW sc-fzoyAV hjJCfv" kind="field" title="point"><span class="sc-fzoXzr iIvtPV"></span>point
                </td>
                <td data-section-id="operation/getGeocode/point" class="sc-fzoLag cxieCN">
                    <div>
                        <div><span class="sc-fzqzlV sc-fzqLLg dxWAkt"></span><span class="sc-fzqzlV sc-fzoxnE dpLlWm">string</span>
                        </div>
                        <div>
                            <div class="sc-fzoant sc-fzoYHE hanQOS"><p><em>Forward geocoding</em>: The location bias in
                                the format 'latitude,longitude' e.g. point=45.93272,11.58803. <em>Reverse geocoding</em>:
                                The location to find amenities, cities.</p>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr class="last undefined">
                <td class="sc-fzqBZW sc-fzoyAV hjJCfv" kind="field" title="provider"><span class="sc-fzoXzr iIvtPV"></span>provider
                </td>
                <td data-section-id="operation/getGeocode/provider" class="sc-fzoLag cxieCN">
                    <div>
                        <div><span class="sc-fzqzlV sc-fzqLLg dxWAkt"></span><span class="sc-fzqzlV sc-fzoxnE dpLlWm">string</span>
                        </div>
                        <div><span class="sc-fzqzlV jDREaU"> Default: </span> <span class="sc-fzqzlV sc-fzonjX iwWBsD">"default"</span>
                        </div>
                        <div>
                            <div class="sc-fzoant sc-fzoYHE hanQOS"><p>The provider parameter is currently under
                                development and can fall back to <code>default</code> at any time.
                                The intend is to provide alternatives to our default geocoder. Each provider has its own
                                strenghts and might fit better for certain scenarios, so it's worth to compare the
                                different providers.
                                To try it append the <code>provider</code>parameter to the URL like <code>&amp;provider=nominatim</code>,
                                the result structure should be identical in all cases - if not, please report this back
                                to us.
                                Keep in mind that some providers do not support certain parameters or don't return some
                                fields, for example <code>osm_id</code> and <code>osm_type</code> are not supported by
                                every geocoding provider.
                                If you would like to use additional parameters of one of the providers, but it's not
                                available for the GraphHopper Geocoding API, yet? Please contact us.</p>
                                <p>The credit costs can be different for all providers - see <a href="https://support.graphhopper.com/support/solutions/articles/44000718211-what-is-one-credit-">here</a>
                                    for more information about it.</p>
                                <p>Currently, only the default provider and gisgraphy supports autocompletion of partial
                                    search strings.</p>
                                <p>All providers support normal "forward" geocoding and reverse geocoding via <code>reverse=true</code>.
                                </p>
                                <h4 id="default-providerdefault">Default (<code>provider=default</code>)</h4>
                                <p>This provider returns results of our internal geocoding engine, as described above.
                                    In addition to the above documented parameters the following parameters are
                                    possible:</p>
                                <ul>
                                    <li><code>bbox</code> - the expected format is
                                        <code>minLon,minLat,maxLon,maxLat</code></li>
                                    <li><code>osm_tag</code> - you can filter <code>key:value</code> or exclude places
                                        with certain OpenStreetMap tags <code>!key:value</code>. E.g. <code>osm_tag=tourism:museum</code>
                                        or just the key <code>osm_tag=tourism</code>. To exclude multiple tags you add
                                        multiple <code>osm_tag</code> parameters.
                                    </li>
                                </ul>
                                <h4 id="nominatim-providernominatim">Nominatim (<code>provider=nominatim</code>)</h4>
                                <p>The GraphHopper Directions API uses a commercially hosted Nominatim geocoder. You can
                                    try this provider <a href="https://nominatim.openstreetmap.org/">here</a>. The
                                    provider does <strong>not</strong> fall under the <a href="https://operations.osmfoundation.org/policies/nominatim/">restrictions</a>
                                    of the Nominatim instance hosted by OpenStreetMap.</p>
                                <p>In addition to the above documented parameters Nominatim allows to use the following
                                    parameters, which can be used as documented <a href="https://github.com/openstreetmap/Nominatim/blob/master/docs/api/Search.md#parameters">here</a>:
                                </p>
                                <ul>
                                    <li><code>viewbox</code> - the expected format is
                                        <code>minLon,minLat,maxLon,maxLat</code></li>
                                    <li><code>bounded</code> - If 1 and a viewbox is given, restrict the result to items
                                        contained within that viewbox. Default is 0.
                                    </li>
                                </ul>
                                <h4 id="gisgraphy-providergisgraphy">Gisgraphy (<code>provider=gisgraphy</code>)</h4>
                                <p>This provider returns results from the Gisgraphy geocoder which you can try <a href="https://services.gisgraphy.com/static/leaflet/index.html">here</a>.</p>
                                <p><strong>Limitations:</strong> The <code>locale</code> parameter is not supported.
                                    Gisgraphy does not return OSM tags or an extent.</p>
                                <p>Gisgraphy has a special autocomplete API, which you can use by adding <code>autocomplete=true</code>
                                    (does not work with <code>reverse=true</code>). The autocomplete API is optimized on
                                    predicting text input, but returns less information.</p>
                                <p>In addition to the above documented parameters Gisgraphy allows to use the following
                                    parameters, which can be used as documented <a href="https://www.gisgraphy.com/documentation/user-guide.php#geocodingservice">here</a>:
                                </p>
                                <ul>
                                    <li><code>radius</code> - radius in meters</li>
                                    <li><code>country</code> - restrict search for the specified country. The value must
                                        be the ISO 3166 Alpha 2 code of the country.
                                    </li>
                                </ul>
                                <h4 id="nettoolkit-providernettoolkit">NetToolKit (<code>provider=nettoolkit</code>)
                                </h4>
                                <p>This provider returns results from the NetToolKit provider which is specialized for
                                    US addresses and provides a wrapper around Nominatim for other addresses. You can
                                    try it <a href="https://www.nettoolkit.com/geo/demo">here</a>.</p>
                                <p>The following additional NetToolKit parameters are supported (read <a href="https://www.nettoolkit.com/docs/geo/geocoding">here</a> for more details):
                                </p>
                                <ul>
                                    <li><code>source</code>: User can choose which source provider to geocode the
                                        address, this value is "NetToolKit" by default
                                    </li>
                                    <li><code>country_code</code>: an iso-3166-2 country code (e.g : US) filter the
                                        results to the specify country code
                                    </li>
                                </ul>
                                <p><strong>Limitations:</strong> NetToolKit does not support the <code>locale</code>
                                    parameter. NetToolKit does not return OSM tags (e.g. osm_id, osm_type, osm_value).
                                </p>
                                <h4 id="opencage-data-provideropencagedata">OpenCage Data
                                    (<code>provider=opencagedata</code>)</h4>
                                <p>This provider returns results from the OpenCageData geocoder which you can try <a href="https://geocoder.opencagedata.com/demo">here</a>.</p>
                                <p>In addition to the above documented parameters OpenCage Data allows to use the
                                    following parameters, which can be used as documented <a href="https://geocoder.opencagedata.com/api#forward-opt">here</a>:</p>
                                <ul>
                                    <li>countrycode - The country code is a two letter code as defined by the ISO 3166-1
                                        Alpha 2 standard. E.g. gb for the United Kingdom, fr for France, us for United
                                        States.
                                    </li>
                                    <li>bounds - the expected format is <code>minLon,minLat,maxLon,maxLat</code></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div><h3 class="sc-psCJM dPLkfP">Responses</h3>
        <div>

            <div class="sc-qapaw iJULWE">
                <table class="sc-fznxsB hSfQgO">
                    <caption class="sc-oTmZL sc-pbIaG iNqipO"> Response Headers</caption>
                    <tbody>
                    <tr>
                        <td class="sc-fzqBZW sc-fzoyAV hjJCfv" kind="field" title="X-RateLimit-Limit"><span class="sc-fzoXzr iIvtPV"></span>X-RateLimit-Limit
                        </td>
                        <td data-section-id="operation/getGeocode/200/X-RateLimit-Limit" class="sc-fzoLag cxieCN">
                            <div>
                                <div><span class="sc-fzqzlV sc-fzqLLg dxWAkt"></span><span class="sc-fzqzlV sc-fzoxnE dpLlWm">integer</span></div>
                                <div>
                                    <div class="sc-fzoant sc-fzoYHE hanQOS"><p>Your current daily credit limit.</p>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="sc-fzqBZW sc-fzoyAV hjJCfv" kind="field" title="X-RateLimit-Remaining"><span class="sc-fzoXzr iIvtPV"></span>X-RateLimit-Remaining
                        </td>
                        <td data-section-id="operation/getGeocode/200/X-RateLimit-Remaining" class="sc-fzoLag cxieCN">
                            <div>
                                <div><span class="sc-fzqzlV sc-fzqLLg dxWAkt"></span><span class="sc-fzqzlV sc-fzoxnE dpLlWm">integer</span></div>
                                <div>
                                    <div class="sc-fzoant sc-fzoYHE hanQOS"><p>Your remaining credits until the
                                        reset.</p>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="sc-fzqBZW sc-fzoyAV hjJCfv" kind="field" title="X-RateLimit-Reset"><span class="sc-fzoXzr iIvtPV"></span>X-RateLimit-Reset
                        </td>
                        <td data-section-id="operation/getGeocode/200/X-RateLimit-Reset" class="sc-fzoLag cxieCN">
                            <div>
                                <div><span class="sc-fzqzlV sc-fzqLLg dxWAkt"></span><span class="sc-fzqzlV sc-fzoxnE dpLlWm">integer</span></div>
                                <div>
                                    <div class="sc-fzoant sc-fzoYHE hanQOS"><p>The number of seconds that you have to
                                        wait before a reset of the credit count is done.</p>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr class="last undefined">
                        <td class="sc-fzqBZW sc-fzoyAV hjJCfv" kind="field" title="X-RateLimit-Credits"><span class="sc-fzoXzr iIvtPV"></span>X-RateLimit-Credits
                        </td>
                        <td data-section-id="operation/getGeocode/200/X-RateLimit-Credits" class="sc-fzoLag cxieCN">
                            <div>
                                <div><span class="sc-fzqzlV sc-fzqLLg dxWAkt"></span><span class="sc-fzqzlV sc-fzoxnE dpLlWm">integer</span></div>
                                <div>
                                    <div class="sc-fzoant sc-fzoYHE hanQOS"><p>The credit costs for this request. Note
                                        it could be a decimal and even negative number, e.g. when an async request
                                        failed.</p>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <h5 class="sc-fzplWN kfkaqq">Response Schema: <span class="sc-fzpkJw hctKJM">application/json</span>
                </h5>
                <table class="sc-fznxsB hSfQgO">
                    <tbody>
                    <tr>

                        <td data-section-id="operation/getGeocode/200/application/json/hits" class="sc-fzoLag cxieCN">
                            <div>
                                <div><span class="sc-fzqzlV sc-fzqLLg dxWAkt">Array of </span><span class="sc-fzqzlV sc-fzoxnE dpLlWm">objects</span><span class="sc-fzqzlV sc-fzoMdx kgimtI"> (GeocodingLocation) </span></div>
                                <div>
                                    <div class="sc-fzoant sc-fzoYHE hanQOS"></div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr class="last undefined">
                        <td class="sc-fzqBZW sc-fzoyAV hjJCfv" kind="field" title="took"><span class="sc-fzoXzr iIvtPV"></span>took
                        </td>
                        <td data-section-id="operation/getGeocode/200/application/json/took" class="sc-fzoLag cxieCN">
                            <div>
                                <div><span class="sc-fzqzlV sc-fzqLLg dxWAkt"></span><span class="sc-fzqzlV sc-fzoxnE dpLlWm">number</span><span class="sc-fzqzlV sc-fzoxnE dpLlWm"> &lt;int64&gt; </span></div>
                                <div>
                                    <div class="sc-fzoant sc-fzoYHE hanQOS"><p>in ms</p>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div>

        </div>
    </div>
</div>

</body>
</body>
</html>
