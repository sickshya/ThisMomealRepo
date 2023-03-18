
    // ▶ 댓글, 대댓글 리스트 출력 ajax
    function commentList(postNo) {

        console.log("글번호 =========" + postNo)
        $.ajax({
                url: "/comment/" + postNo,
                type: 'post',
                dataType: 'json', // 데이터 타입을 Json으로 변경
                contentType: 'application/json' // Content-Type을 Json으로 변경
            })
            .then(data => {
                console.log("댓글 ======== " + data);
                
                // 댓글창 비우기
                $("#reply").empty()

				// 댓글이 없는 경우
				if(data.length == 0) {
					$("#reply").append(
						`<p style="color:gray; text-align:center;">댓글이 없습니다.</p>`
					)
				}

                // 댓글창 채우기
                $(data).each(function (index, item) {
                    console.log(item.profileImgPath);
					
					// 조건에 따른 댓글 버튼 출력
					let commentBtn = '';
                    if(sessionId != null) {
						if(item.userId == sessionId) {
							commentBtn = `<button type="button" style="float: right; font-size:12px;" onclick="cmmtDelete(${item.no})">삭제</button>&nbsp;
				                           <button type="button" style="float: right; font-size:12px;" onclick="updateBox('${item.subject}', ${item.cmmtGroup}, ${item.no})">수정</button>
				                           <button type="button" style="float: right; font-size:12px;" id="cmmtReply${item.no}" onclick="reply(${item.cmmtGroup}, ${item.no})">답글</button>`
						}else {
							commentBtn = `<button type="button" style="float: right; font-size:12px;" id="cmmtReply${item.no}" onclick="reply(${item.cmmtGroup}, ${item.no})">답글</button>`
						}
					}
                    
                    
                    // 1) 댓글일때
                    // 댓글 쓴 아이디가 세션의 아이디와 같다면 수정, 삭제 기능 / 다르다면 답글 기능
                    if (item.cmmtClass == 0) {
                        $("#reply").append(
                            `<div class="flex flex-1 space-x-2" id="comment${item.no}">
                    <a href="/myFeed/${item.userId}">   
                    <img src="${item.profileImgPath}"
                          class="rounded-full w-8 h-8"></a>
                       <div class="flex-1" id="one${item.no}">
                       <p id="p${item.no}"><b>${item.nickName}</b> 
                       		<span style="font-size:10px;">${item.writeDate}</span>
                       ${commentBtn}</p>
                       <div id="subject${item.no}"><p>${item.subject}</p></div></div>
                       <input type="hidden" id="cmmtClass${item.no}" value="0">
                       <input type="hidden" id="cmmtOrder${item.no}" value="${item.cmmtOrder}">
                    </div>`
                        )
                    }
                    // 2) 대댓글일때
                    // 대댓글 쓴 아이디가 세션의 아이디와 같다면 수정, 삭제 기능
                    if (item.cmmtClass != 0) {
                        $("#reply").append(
                            `<div class="flex flex-1 space-x-2" style="padding-left:25px;">
		                       <img src="${item.profileImgPath}"
		                          class="rounded-full w-8 h-8">
		                       <div class="flex-1" id="replyOne${item.no}"><p id="p${item.no}"><b>${item.nickName}</b> <span style="font-size:10px;">${item.writeDate}</span>
		                       ${item.userId == sessionId ? 
		                          `<button type="button" style="float: right; font-size:12px;" onclick="cmmtDelete(${item.no})">삭제</button>&nbsp;
		                          <button type="button" style="float: right; font-size:12px;" onclick="updateBox('${item.subject}', ${item.cmmtGroup}, ${item.no})">수정</button>` : ``}</p>
		                          <div id="subject${item.no}"><p>${item.subject}</p></div></div>
		                          <input type="hidden" id="cmmtClass${item.no}" value="1">
		                          <input type="hidden" id="cmmtOrder${item.no}" value="${item.cmmtOrder}">
                    		 </div> `
                        )
                    }
                });
            })
    };


    // ▶ 댓글 등록
    function cmmtInsert() {
        event.preventDefault()

        let cmmtSubject = $('#cmmtSubject').val();
        let postNo = $('#postNo').val();
        let scrollDiv = $('#scrollDiv');

        // 내용이 없을 경우 ajax 이동 차단
        if (cmmtSubject == '') {
            alert("내용을 입력해주세요.")
            return false;
        }

        // ajax 처리
        $.ajax({
                type: 'post',
                url: '/insertComment/' + postNo,
                data: {
                    "subject": cmmtSubject
                }
            })
            .then(result => {
                console.log(result)
                $('#cmmtSubject').val("");
                commentList(postNo);
                $("#scrollDiv .simplebar-content").scrollTop($("#scrollDiv .simplebar-content")[0].scrollHeight);
            })

    }

    // ▶ 답글 등록
    function replyInsert(cmmtGroup, no) {

        event.preventDefault()

        let replySubject = $('#replySubject').val();
        let postNo = $('#postNo').val();
        let scrollDiv = $('#scrollDiv');

        // 내용이 없을 경우 ajax 이동 차단
        if (replySubject == '') {
            alert("내용을 입력해주세요.")
            return false;
        }

        // ajax 처리
        $.ajax({
                type: 'post',
                url: '/insertReply/' + postNo,
                data: {
                    "subject": replySubject,
                    "cmmtGroup": cmmtGroup
                }
            })
            .then(result => {
                console.log(result)
                $('#replyBox' + no).remove();
                commentList(postNo);
                // 답글은 제일 밑으로 내려가면 안되는데..ㅠ
                //             $("#scrollDiv .simplebar-content").scrollTop($("#scrollDiv .simplebar-content")[0].scrollHeight);
            })
    }


    // ▶ 답글창 생성
    function reply(cmmtGroup, no) {
        let postNo = $('#postNo').val();
        let comment = $('#one' + no);
        let reply = $('#cmmtReply' + no);

        // 답글창 붙이기
        comment.append(
            `<div class="p-3 border-t dark:border-gray-600" id="replyBox${no}">
           <div
              class="bg-gray-200 dark:bg-gray-700 rounded-full rounded-md relative">
              <input type="text" placeholder="댓글을 입력하세요."
                 class="bg-transparent max-h-8 shadow-none" id="replySubject">
              <div
                 class="absolute bottom-0 flex h-full items-center right-0 right-3 text-xl space-x-2">
                 <a href=""><button type="button" style="font-size:10px;" onclick="replyInsert(${cmmtGroup}, ${no})">등록</button></a>
              </div>
           </div>
        </div>`
        )

        reply.attr("onclick", "unreply(" + cmmtGroup + "," + no + ")")

    }


    // ▶ 답글창 닫기
    function unreply(cmmtGroup, no) {
        let comment = $('#one' + no);
        let reply = $('#cmmtReply' + no);
        let replyBox = $('#replyBox' + no);

        replyBox.remove()
        reply.attr("onclick", "reply(" + cmmtGroup + "," + no + ")")
    }


    // ▶ 댓글 수정창 열기
    function updateBox(subject, cmmtGroup, no) {

        let commentBox = $('#subject' + no);
        let comment = commentBox.text();
        commentBox.empty()

        let cmmtClass = $('#cmmtClass' + no).val();
        console.log("댓글 클래스 =====" + cmmtClass);

        // 버튼 지우기
        if ($('#cmmtClass' + no).val() == 0) {
            $('#one' + no).find('button').remove();
        } else {
            $('#replyOne' + no).find('button').remove();
        }

        // 취소 버튼 추가
        $('#p' + no).append(
            `<button type="button" style="float: right; font-size:12px;" onclick="noUpdate('${subject}', ${no})">취소</button>`
        )

        // 댓글 수정창 생성
        commentBox.append(
            `<div id="updateBox${no}">
              <div
                 class="bg-gray-200 rounded-full rounded-md relative">
                 <input type="text" placeholder="댓글을 입력하세요."
                    class="bg-transparent max-h-8 shadow-none" id="updateSubject" value="${comment}">
                 <div
                    class="absolute bottom-0 flex h-full items-center right-0 right-3 text-xl space-x-2">
                    <a href=""><button type="button" style="font-size:10px;" onclick="commentUpdate(${no})">수정</button></a>
                 </div>
              </div>
           </div>`
        )
    }


    // ▶ 댓글 수정 기능 (수정)
    function commentUpdate(no) {
        //          debugger
        event.preventDefault()

        let updateSubject = $('#updateSubject').val();
        let postNo = $('#postNo').val();

        // 내용이 없을 경우 ajax 이동 차단
        if (updateSubject == '') {
            alert("내용을 입력해주세요.")
            return false;
        }

        // ajax 처리
        $.ajax({
                type: 'post',
                url: '/updateComment/' + no,
                data: {
                    "subject": updateSubject,
                    "no": no
                }
            })
            .then(result => {
                console.log(result)

                commentList(postNo);
            })
    }


    // ▶ 댓글 수정 취소
    function noUpdate(subject, no) {

        event.preventDefault()

        let postNo = $('#postNo').val();

        // ajax 처리 (기존의 댓글 내용으로 그냥 수정 처리함)
        $.ajax({
                type: 'post',
                url: '/updateComment/' + no,
                data: {
                    "subject": subject,
                    "no": no
                }
            })
            .then(result => {
                console.log(result)

                commentList(postNo);
            })
    }


    // ▶ 댓글 삭제
    function cmmtDelete(no) {
        let postNo = $('#postNo').val();

        // ajax 처리
        $.ajax({
                type: 'post',
                url: '/deleteComment/' + no
            })
            .then(result => {
                console.log(result)
                commentList(postNo);
            })
    }
